FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y --no-install-recommends \
  bash ca-certificates curl git unzip wget openjdk-17-jdk \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /workspace

COPY build.sh /workspace/build.sh
RUN chmod +x /workspace/build.sh

ENTRYPOINT ["/workspace/build.sh"]
