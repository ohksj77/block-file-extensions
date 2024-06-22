# block-file-extensions

### _파일 확장자 차단_

[todo 문서](./docs/todo.md)

[API Docs](https://ohksj77.github.io/block-file-extensions-api-docs/)

## 화면 설명과 고려 사항
1. 로그인 화면
- 회원별로 확장자 및 파일을 관리하고자 회원 기능을 구현했습니다.
- 하단 미리 저장된 계정 정보는 flyway를 통해 미리 저장한 회원 더미데이터입니다.
- 사용하기(자동입력) 을 입력하시면 Username과 Password가 자동 입력되며 Login 버튼으로 로그인하시면 됩니다.
- 로그인 이후에는 로그인 화면에 접속 불가합니다. (자동 리다이렉션 됩니다.)
![](https://github.com/ohksj77/block-file-extensions-api-docs/assets/89020004/bb0e6a77-bd44-40a2-8f54-84821390dc17)

2. 회원가입 화면
- username과 password를 3 ~ 10 자 이내로 입력하시면, username이 중복되지 않은 경우 회원가입이 완료됩니다.
![](https://github.com/ohksj77/block-file-extensions-api-docs/assets/89020004/d0938988-3376-4a40-94e4-797313230b19)

3. 메인 화면
- 고정 확장자와 커스텀 확장자를 입력 가능하며 파일을 선택해 선택되지 않은 확장자에 한해 업로드가능합니다. 
- 본인이 업로드한 파일 목록을 확인할 수 있으며 클릭하면 다운로드됩니다.
- 메인 화면의 모든 정보는 회원별로 관리되며 로그아웃 후 재접속하여도 유지됩니다.
- 로그인하지 않은 경우 메인 화면에 접속 불가합니다.
- 커스텀 확장자는 중복이 불가하며, 고정 확장자와도 중복이 불가합니다.
![](https://github.com/ohksj77/block-file-extensions-api-docs/assets/89020004/9e1f7e4a-c1eb-4ffc-beed-71e7c656b1b4)
![](https://github.com/ohksj77/block-file-extensions-api-docs/assets/89020004/aef654cf-00f2-44c3-8e28-172e93672b6f)

## 코드레벨 구현시 고려 사항

*각 토글을 클릭하시면 설명이 적혀있습니다.

<details>
<summary>파일 확장자 저장 자료구조 HashSet, LinkedHashSet 선택</summary>
<div markdown="1">

- Extension 엔티티는 파일 확장자를 나타내며, 고정 확장자 리스트와 커스텀 확장자 리스트를 가집니다.
- 두 리스트를 담기 위한 자료구조를 고민하며 사용자가 추가 요청한 확장자가 존재하는지 확인에 용이하기 위해 Set 자료구조를 활용하며, 화면상 순서가 고정된 고정 확장자는 HashSet으로, 커스텀 확장자의 경우 순서를 직접 고정시키기 위해 LinkedHashSet을 사용했습니다.

![](https://github.com/ohksj77/block-file-extensions-api-docs/assets/89020004/fa299b9a-8d8d-4ce3-95e4-7c075a58c071)

</div>
</details>

<details>
<summary>연관관계 매핑 대신 id 필드 저장</summary>
<div markdown="1">

- 서로 연관관계에 있어야할 엔티티 사이에 db pk만 가지게 구현한 부분이 있습니다.
- JPA의 연관관계를 통해 서로를 조회하기 보다는 db pk를 통해 조회만 수행하는 경우에 이러한 방법을 택하며 불필요한 관리 포인트를 줄였으며 연관관계로 인해 생길 이슈들을 생각하지 않아도 되었습니다.

![](https://github.com/ohksj77/block-file-extensions-api-docs/assets/89020004/b47e494f-f75b-4937-9759-c92889ef191a)

</div>
</details>

<details>
<summary>파일 업로드 로직 변경을 고려한 인터페이스 구현</summary>
<div markdown="1">

- 파일 업로드/다운로드를 담당하는 인터페이스를 생성해 이를 사용하는 곳에서 주입받도록 구현했습니다.
- 현재는 파일을 로컬에 저장하지만, s3 등의 클라우드 스토리지 사용을 고려하기 위해 이러한 구조를 가져갔습니다.

![](https://github.com/ohksj77/block-file-extensions-api-docs/assets/89020004/5ba39d28-9ad2-48e4-a8e7-8f27f22af681)

</div>
</details>

<details>
<summary>시큐리티로 회원 db pk를 가져오기 위한 ArgumentResolver 구현</summary>
<div markdown="1">

- 인증 이후 회원의 db pk를 편하게 가져와 생산성을 높이고자 구현했습니다.

![](https://github.com/ohksj77/block-file-extensions-api-docs/assets/89020004/5647f7e9-0d72-43d5-8a18-ccfa566ceb04)

</div>
</details>

## 사용 컴포넌트
```
- Spring Boot (ver 3.3.0)
    - java 17
    - gradle
    - spring-data-jpa
    - spring-security
    - spring-rest-docs
    - thymeleaf
        - jquery
    - flyway
- MySQL
- Jib && docker-compose
```

## ERD
![ERD](https://github.com/ohksj77/block-file-extensions-api-docs/assets/89020004/df190833-f66a-4bc3-bffb-0bc25e128747)

## 실행 방법
도커를 통한 실행시
```
docker-compose -f docker-compose.prod.yml up -d
```
DB만 도커로 실행시 (하단 커맨드 이후 IDE를 통한 서버 실행)
```
docker-compose up -d
```

## Connection 정보
```
host: localhost
port: 3306
root password: 1234
username: admin
password: 1234
```

## ETC
- 민감한 정보의 환경변수도 번거로운 작업을 줄이기 위해 그대로 커밋하였습니다.
