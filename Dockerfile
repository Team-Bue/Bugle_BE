# -----------------------------
# 1️⃣ Build Stage
# -----------------------------
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

# Gradle Wrapper 복사
COPY gradlew ./
COPY gradle gradle/
RUN chmod +x ./gradlew

# 의존성 먼저 복사하여 캐싱 최적화
COPY build.gradle settings.gradle ./
RUN ./gradlew dependencies --no-daemon

# 소스 코드만 복사 (generated 제외)
COPY src/main/java src/main/java/
COPY src/main/resources src/main/resources/

# QueryDSL 생성 및 빌드
RUN ./gradlew clean bootJar --no-daemon -x test

# -----------------------------
# 2️⃣ Run Stage
# -----------------------------
FROM eclipse-temurin:17-jre
WORKDIR /app

# 보안을 위한 non-root 유저 생성
RUN groupadd -r spring && useradd -r -g spring spring

# JAR 파일 복사
COPY --from=build --chown=spring:spring /app/build/libs/app.jar app.jar

# non-root 유저로 전환
USER spring:spring

EXPOSE 8080

# JVM 최적화 옵션 추가
ENTRYPOINT ["java", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=75.0", \
    "-XX:+UseG1GC", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-jar", \
    "app.jar"]
