# 20250901
 
## 1. Thread
프로그램(프로세스) 내에서 실행되는 작업의 단위·흐름

1) 하나의 자바프로세스에는 최소 1개의 스레드가 존재
2) 자바는 main() 함수 실행시 main 스레드 1개가 실행

## 2. Multi-Thread
1) main thread 외 새로운 작업 스레드 실행
2) 병렬처리 시 주로 이용
= 동시에 여러 작업을 수행

## 3. Spring-Thread
Spring Framework는 기본적으로 Multi-Thread 환경을 지원
    vs Node.js : 단일 thread / 소규모에 적합