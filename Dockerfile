FROM gradle:7.6.4-jdk17
WORKDIR /app
COPY . /app
CMD ["gradle", "bootRun"]
