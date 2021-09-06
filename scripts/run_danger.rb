require 'danger'

begin
  Danger::Runner.run ["--verbose", "--fail-on-errors=true"]
rescue Exception => e
  if e.message.include? "Are you running `danger local/pr` against the correct repository? Also this can happen if you run danger on MR without changes"
      puts "Empty branch, skip this job"
      exit(0)
  else
      puts e.message
      exit(1)
  end
end
