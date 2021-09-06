#!/bin/bash

# Gitlab constants
GITLAB_CI_MR_API="https://git.epam.com/api/v4/projects/${CI_PROJECT_ID}/merge_requests"
GITLAB_CI_API_AUTHENTICATION_HEADER="PRIVATE-TOKEN: $DANGER_GITLAB_API_TOKEN"

# Approve constants
LEADS_NAMES=(pavel_verkhovtsov Viyaleta_Kazlouskaya)
TEAM_NAMES=(alena_kemiazhuk aliaksei_kachan bagzhan_sadvakassov pavel_anhur uladzislau_samykou)
APPROVE_EMOJI="thumbsup"
DEFAULT_APPROVE_COUNT=1

## Parse parameters
while [[ $# -gt 0 ]]
do
	key="$1"

	case $key in
		-l|--leads)
		LEADS_REVIEW=true
		shift # past argument
		;;
		-t|--team|--pear)
		PEAR_REVIEW=true
		shift # past argument
		;;
		-a|-c|--approves-count)
		MIN_APPROVES_COUNT="$2"
		shift # past argument
		shift # past parameter
		;;
		*)    # unknown option
		shift # past argument
		;;
	esac
done

# Validate input
if [[ ! $MIN_APPROVES_COUNT ]]
then
		echo "WARNING: Required approves count isn't selected. Used default value: $DEFAULT_APPROVE_COUNT"
		MIN_APPROVES_COUNT=$DEFAULT_APPROVE_COUNT
fi

if [[ $LEADS_REVIEW ]] && [[ $PEAR_REVIEW ]]
then
		echo "ERROR: Incorrect params. Please select single review type."
		exit 1
fi

if [[ ! $LEADS_REVIEW ]] && [[ ! $PEAR_REVIEW ]]
then
		echo "WARNING: Review type wasn't set explicitly. Leads review type will be used."
		LEADS_REVIEW=true
fi

MERGE_REQUEST_ID=$(curl --header "$GITLAB_CI_API_AUTHENTICATION_HEADER" -s "$GITLAB_CI_MR_API?state=opened" | jq -r ".[]|select(.sha == \"$CI_COMMIT_SHA\")|.iid")
echo "Current merge request identifier is $MERGE_REQUEST_ID"

AUTHOR=$(curl --header "$GITLAB_CI_API_AUTHENTICATION_HEADER" -s "$GITLAB_CI_MR_API/$MERGE_REQUEST_ID" | jq .author.username)
AUTHOR="${AUTHOR%\"}"
AUTHOR="${AUTHOR#\"}"
echo "MR author is $AUTHOR"

if [[ $LEADS_REVIEW ]]
then
  NAMES=("${LEADS_NAMES[@]}")
elif [[ $PEAR_REVIEW ]]
then
  NAMES=(${TEAM_NAMES[@]/$AUTHOR})
  if [ "${#NAMES[@]}" -eq "${#TEAM_NAMES[@]}" ]; then
    echo "Author is not in team. Peer-review skipped"
	  exit 0
  fi
fi

# Build pattern for check is approver set target emoji
PRE_APPROVERS__PATTERN="$(printf ".user==\"%s\" " "${NAMES[@]}" | awk '{$1=$1};1')"
APPROVERS_PATTERN="${PRE_APPROVERS__PATTERN[*]// / or }"
APPROVE_CONDITION="[.[] | ($APPROVERS_PATTERN) and (.emoji==\"$APPROVE_EMOJI\")]"

LIST_OF_MR_EMOJI=$(curl --header "$GITLAB_CI_API_AUTHENTICATION_HEADER" -s "$GITLAB_CI_MR_API/$MERGE_REQUEST_ID/award_emoji" | jq '[.[] | {emoji: .name, user: .user.username}]' )
APPROVE_RESULTS=$(echo $LIST_OF_MR_EMOJI | jq "$APPROVE_CONDITION")
MR_APPROVES_COUNT=$(echo $APPROVE_RESULTS | jq  'reduce .[] as $item (0; . + if $item == true then 1 else 0 end)')
SECRET_CONDITION=$(echo $LIST_OF_MR_EMOJI | jq --arg SECRET_APPROVE_CONDITION "$SECRET_APPROVE_CONDITION" '[.[] | .emoji==$SECRET_APPROVE_CONDITION ] | reduce .[] as $item (false; . or $item)')

# Check main condition is MR approved
if [ $MR_APPROVES_COUNT -ge $MIN_APPROVES_COUNT ] || [ "$SECRET_CONDITION" = true ];
then
	echo "Code review passed! Congratulations."
	exit 0
else
	echo "The MR is still under review"
	exit 1
fi