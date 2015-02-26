#!/usr/bin/env sh

java -Xms5g -Xmx10g \
  -cp target/velox-parent-0.0.1-SNAPSHOT.jar edu.berkeley.MyMain server config.yml
