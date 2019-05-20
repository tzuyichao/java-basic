export JAVA_HOME=$(/usr/libexec/java_home)

jsvc -server -cwd /Users/tzuyichao/lab/projects/java-basic/daemon-lab -cp lib/*:build/libs/daemon-lab-1.0-SNAPSHOT.jar -outfile /tmp/out.log -errfile /tmp/err.log -pidfile /tmp/pid.file scheduler.main.SchedulerDaemon -stop
