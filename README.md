# 📌 Spring Boot 기반 REST API 개인 프로젝트

## 📝 프로젝트 소개
Spring Boot와 MySQL 기반의 RESTful 게시판 API입니다.  
**회원가입, 이메일 인증, 비밀번호 복잡성 검증, 게시글 CRUD 등** 실무에서 자주 쓰는 백엔드 핵심 기능을 직접 구현했습니다.  
MapStruct, JavaMailSender, JWT 등 주요 라이브러리를 활용해 확장성과 실용성에 집중했습니다.

---

## 🚀 주요 기능

- **회원가입**
  - 이메일 중복 검증
  - 비밀번호 복잡성(특수문자 포함) 검증
  - 이메일 인증(인증 메일 발송 & 인증 처리)
- **로그인** (JWT 토큰 발급)
- **게시글**
  - 등록 / 조회 / 수정 / 삭제 (CRUD)
- **비밀번호 유효성 검사**
  - `PasswordConstraintValidator`를 통한 특수문자 포함 등 커스텀 검증
- **API 응답 DTO 변환**
  - MapStruct를 이용한 Entity-DTO 자동 매핑

---

## 🛠️ 사용 기술 & 라이브러리

| 기술/라이브러리   | 내용                               |
|-------------------|------------------------------------|
| Backend           | Spring Boot 3.5.3                  |
| DB                | MySQL                              |
| ORM               | Spring Data JPA                    |
| Email 인증        | JavaMailSender                     |
| DTO 매핑          | MapStruct 1.5.5.Final              |
| JWT 인증          | jjwt 0.11.5                        |
| Validation        | Hibernate Validator                |
| 빌드 툴           | Gradle                             |
| Java              | 21                                 |
| 테스트            | JUnit, Spring Boot Test            |
| 기타              | Lombok                             |

---

## 🏃‍♂️ 실행 방법

1. **필수 환경 변수 등록**
    - `.env` 파일 또는 `application.yml`에 아래 항목을 설정
      ```
      DATABASE_USERNAME=...
      DATABASE_PASSWORD=...
      DRIVER_CLASS_NAME=...
      URL=jdbc:mysql://...
      JWT_SECRET=...
      MAIL_USERNAME=...
      MAIL_PASSWORD=...
      ```
2. **MySQL 실행 & DB 생성**
3. **Gradle 빌드 & 실행**
    ```bash
    ./gradlew build
    ./gradlew bootRun
    ```
4. **API 테스트**
    - Postman/Swagger 등으로 API 요청 테스트
    - (이메일 인증 시, 실제 메일로 발송됨)

---

## 🗂️ 폴더 구조 (예시)

#### src
#### └─ main
#### ├─ java
#### │ └─ com.example
#### │ ├─ controller
#### │ ├─ dto
#### │ ├─ entity
#### │ ├─ mapper
#### │ ├─ repository
#### │ ├─ service
#### │ ├─ validator
#### │ └─ config
#### └─ resources
#### └─ application.yml

---

## 🛡️ 비밀번호 검증 로직

- `PasswordConstraintValidator`에서 커스텀 로직(특수문자 포함, 길이 등) 검증
- 조건 불충족 시 회원가입 거부 & 에러 메시지 반환

---

## 📮 이메일 인증

- 회원가입 시 인증 메일 발송
- 인증코드 클릭/입력 시 계정 활성화
- JavaMailSender 라이브러리 활용

---

## 🔄 엔티티-DTO 변환

- **MapStruct**로 Entity ↔ DTO 자동 변환
- 서비스/컨트롤러 코드 단순화

---

## 📖 주요 API 예시

| 기능       | 메서드 | 경로            | 설명                |
|------------|--------|-----------------|---------------------|
| 회원가입   | POST   | /api/signup     | 이메일 인증 포함    |
| 로그인     | POST   | /api/login      | JWT 토큰 발급       |
| 게시글 생성| POST   | /api/posts      | 게시글 등록         |
| 게시글 조회| GET    | /api/posts/{id} | 게시글 단건 조회    |
| 게시글 수정| PUT    | /api/posts/{id} | 게시글 수정         |
| 게시글 삭제| DELETE | /api/posts/{id} | 게시글 삭제         |

---

## ✍️ 개발 포인트 & 차별성

- **실제 서비스에서 요구하는**  
  비밀번호 복잡성 검사, 이메일 인증 기능 직접 구현
- **MapStruct**로 깔끔한 Entity-DTO 변환 구조
- **보안/유효성 강화:** Hibernate Validator, JWT 활용
- 학습용뿐 아니라 실전 프로젝트/협업용 코드 구조로 설계

---

## 🙋‍♂️ Author

- 이름: [당신 이름]
- 이메일: [이메일]
- (포트폴리오/블로그 링크 등)

---

## 🏷️ License

MIT

