require 'yaml'
require 'active_support'
require 'active_support/core_ext'
require 'active_support/deprecation'

#################################
####### PRE-CONDITIONS ##########
#################################

# Skip this MR check if the source branch is master
# We don't want Danger to be run on those branches.
source_branch = gitlab.mr_json["source_branch"]
is_master_branch = source_branch == 'master'
return if is_master_branch

#################################
########## ERRORS ###############
#################################

# Check if the squash option is selected
unless gitlab.mr_json["squash"]
  fail "Please check the squash option in the merge request settings."
end

# Check if the delete source branch option is selected
unless gitlab.mr_json["force_remove_source_branch"]
  fail "Please check the delete source branch option in the merge request settings."
end

unless gitlab.mr_json["description"] =~ /JIRA link:\s*((https:\/\/jira\.epam\.com\/jira\/browse\/EPMFARMATS-\d{5,6})|(non-Jira issue))/
	fail "Please add link on Jira ticket into MR description"
end

#################################
########## WARNINGS #############
#################################

#Huge MR
if git.lines_of_code > 1000
  warn "Tell me please, why this Merge Request is so huge?"
end

# Check commit message
if !gitlab.mr_title.include? git.commits.last.message
  warn "Think about our nice changelog. Put merge request's title in the first commit message"
end
