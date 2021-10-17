# Simple Akka Http plain server

## Deliver as docker image

The application is delivered as a docker image.   
At first the application is packaged as a fat jar with [sbt-assembly plugin](https://github.com/sbt/sbt-assembly), then
the jar is placed inside a docker image with [sbt-docker plugin](https://github.com/marcuslonnberg/sbt-docker).

#### sbt task for docker

* `sbt docker`: build the image in local docker repository
* `sbt dockerPush`: pushes the latest build image on remote repository (same as `docker push <image>`)
* `sbt dockerBuildAndPush`: build the image and pushes it on remote repository (same as both previous command combined)
