# ️밥풀 BAPPOOL

- 배포 URL : http://hana-fe.s3-website.ap-northeast-2.amazonaws.com/
- Test ID : user
- Test PW : 1234

<br>

## 프로젝트 소개

- 밥풀(BAPPOOL)은 혼밥의 외로움을 달래고, 새로운 사람들과의 즐거운 만남을 만들어주는 밥 약속 매칭 서비스입니다.
- 사용자는 밥 약속을 생성하거나 참여하여 함께 식사할 사람들을 찾을 수 있습니다.
- 관심사가 같은 사람들과 편안하게 밥을 먹으며 친목을 다질 수 있습니다.

<br>

## 팀원 구성

<div align="center">

| **윤다빈** | **권승목** | **조수연** |
| :------: |  :------: | :------: |
|![image](https://github.com/user-attachments/assets/8fd9c59b-16cf-4427-a4ac-4e58aa762364)  | ![image](https://github.com/user-attachments/assets/6c033e6b-b806-4afb-a6cd-a33bffa5a5cc) | ![image](https://github.com/user-attachments/assets/14c20545-7be0-4f00-90f7-05c89417d76a) |
| [GitHub](https://github.com/yoodab) | [GitHub](https://github.com/Seungmok1) | [GitHub](https://github.com/Josuyeon89)|

</div>

<br>

## 1. 개발 환경

- Front : React, styled-components
- Back-end : Spring Boot, WebSocket, MySQL
- 버전 및 이슈관리 : Github, Github Issues
- 협업 툴 : Discord, Notion
- 서비스 배포 환경 : AWS EC2, AWS S3
- 디자인 : Figma
- [커밋 컨벤션]([커밋 컨벤션 링크])
- [코드 컨벤션]([코드 컨벤션 링크])

<br>

## 2. 채택한 개발 기술과 브랜치 전략

### React, styled-component

- React
    - 컴포넌트화를 통해 추후 유지보수와 재사용성을 고려했습니다.
- styled-component
    - props를 이용한 조건부 스타일링을 활용하여 상황에 알맞은 스타일을 적용시킬 수 있었습니다.
    - 빌드될 때 고유한 클래스 이름이 부여되어 네이밍 컨벤션을 정하는 비용을 절약할 수 있었습니다.

### Spring Boot, WebSocket, MySQL

- Spring Boot
    - 빠른 개발 속도와 안정적인 성능을 제공하여 선택했습니다.
- WebSocket
    - 실시간 채팅 기능 구현을 위해 선택했습니다.
- MySQL
    - 안정적인 데이터 관리를 위해 선택했습니다.

### AWS EC2, AWS S3

- 서비스 배포 및 운영을 위해 선택했습니다.


### 브랜치 전략

- Git-flow 전략을 기반으로 main, develop 브랜치와 feature 보조 브랜치를 운용했습니다.
- main, develop, Feat 브랜치로 나누어 개발을 하였습니다.
    - **main** 브랜치는 배포 단계에서만 사용하는 브랜치입니다.
    - **develop** 브랜치는 개발 단계에서 git-flow의 master 역할을 하는 브랜치입니다.
    - **Feat** 브랜치는 기능 단위로 독립적인 개발 환경을 위하여 사용하고 merge 후 각 브랜치를 삭제해주었습니다.

<br>

## 3. 프로젝트 구조
```
main
 ├─java
 │  └─org
 │      └─example
 │          └─hana
 │              ├─chatting
 │              │  ├─controller
 │              │  │  └─dto
 │              │  ├─entity
 │              │  └─repository
 │              ├─global
 │              │  ├─common
 │              │  ├─config
 │              │  ├─exception
 │              │  ├─jwt
 │              │  └─util
 │              ├─recruitment
 │              │  ├─controller
 │              │  ├─entity
 │              │  ├─model
 │              │  ├─repository
 │              │  └─service
 │              ├─review
 │              │  ├─controller
 │              │  │  └─dto
 │              │  ├─entity
 │              │  ├─repository
 │              │  └─service
 │              │      └─info
 │              └─user
 │                  ├─controller
 │                  ├─dto
 │                  ├─entity
 │                  ├─repository
 │                  └─service
 └─resources
```
 

<br>

## 4. 역할 분담

### 윤다빈

- **UI**
    - [팀원 1이 담당한 UI 페이지 및 컴포넌트 명시]
- **기능**
    - [팀원 1이 담당한 기능 명시]

<br>

### 권승목

- **UI**
    - [팀원 2가 담당한 UI 페이지 및 컴포넌트 명시]
- **기능**
    - [팀원 2가 담당한 기능 명시]

<br>

### 조수연

- **UI**
    - [팀원 3이 담당한 UI 페이지 및 컴포넌트 명시]
- **기능**
    - 사용자 인증 (JWT 회원가입 및 로그인)
    - 보안 기능

<br>

## 5. 개발 기간 및 작업 관리

### 개발 기간

- 전체 개발 기간 : [25.03.10] ~ [25.03.12]
- [필요한 경우 UI 구현, 기능 구현 등 세부 기간 명시]

<br>

### 작업 관리

- GitHub Projects와 Issues를 사용하여 진행 상황을 공유했습니다.

<br>

## 6. 신경 쓴 부분

- [신경 쓴 부분 1 설명]
- [신경 쓴 부분 2 설명]
- [필요한 경우 추가 설명]

<br>

## 7. 페이지별 기능

### 밥 약속 생성

- 사용자는 약속을 생성할 수 있으며, 날짜, 시간, 장소, 인원을 설정할 수 있습니다.
- 주변의 밥 약속 목록을 확인하고 원하는 약속에 참여할 수 있습니다.

<br>

### 채팅 기능

- 실시간 채팅을 통해 약속 참가자들 간에 소통할 수 있습니다.
- 약속 정보를 공유하고, 약속 일정 및 장소에 대한 세부 조정을 할 수 있습니다.
- WebSocket을 이용하여 빠른 메시지 전송과 안정적인 실시간 커뮤니케이션을 지원합니다.

<br>

### 리뷰 작성

- 식사 후 리뷰를 남길 수 있으며, 음식점에 대한 평가를 기록할 수 있습니다.
- 리뷰는 다른 사용자가 참고할 수 있으며, 댓글 기능을 활용하여 자유롭게 의견을 남길 수 있습니다.

<br>

### 회원 정보 관리

- 사용자는 회원 정보를 수정하고 관리할 수 있습니다.

<br>

## 8. 트러블 슈팅

- [트러블 슈팅 1 내용 및 해결 과정 설명]
- [트러블 슈팅 2 내용 및 해결 과정 설명]
- [필요한 경우 추가 트러블 슈팅 명시]

<br>

## 9. 개선 목표

- [개선 목표 1 설명]
- [개선 목표 2 설명]
- [필요한 경우 추가 개선 목표 명시]

<br>

## 10. 프로젝트 후기

### 윤다빈

[팀원 1의 프로젝트 후기 작성]

<br>

### 권승목

[팀원 2의 프로젝트 후기 작성]

<br>

### 조수연

 - 짧은 시간동안 진행하는 프로젝트이다보니 완성에 급급해 맡은 부분에 대한 이해를 완벽히 하지 못한채 넘어간 경우가 많았다. 프로젝트 끝나면 어느정도 이해가 되겠지라는 생각으로 일단 코드 완성을 했는데 이해가 제대로 되지 않아 협업할 때 질문에 대답하지 못하는 경우도 발생하면서 기존에 가지고 있단 방식이 잘못되었다는 것을 실감하게 되었다. 또한 이번 프로젝트에서 AI의 도움을 많이 빌리게 되었는데 이러한 AI를 잘 활용하는것이 중요하다고 느꼈다. 
해커톤은 처음이었는데 빠른 속도로 프론트엔드와 백엔드를 완성하면서 전반적인 개발 프로세스를 빠르게 이해할 수 있어 좋은 경험이었고 많은 것들을 배우고 느낀 시간이었다.
