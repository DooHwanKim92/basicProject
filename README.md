## WSMS(Working State Management System)

### 서비스 설명

기업에서 효율적인 인적자원 관리를 돕고 직원들의 근태와 생산성과의 상관관계를 도출하여
운영방향 결정에 도움을 줄 수 있는 서비스.

[[기능정의서](https://docs.google.com/document/d/1JhillVH7krKQHiItRMYs4i9Wcmvan_NZEfTKdfN22rM/edit?usp=sharing)]

---

## 🛠 개발환경

1. JDK : Amazon Corretto version 17.0.9
2. 에디터 : IntelliJ IDEA
3. DB종류 : MySQL


---

## ☁️ ERD ([링크](https://www.erdcloud.com/d/tpBNmJpZi28uhzXBa))

![](https://velog.velcdn.com/images/asdf4321/post/68109fe5-48e0-49ee-8ccd-b310955f1f65/image.png)



---

## 👀 시연영상

[![Video Label](http://img.youtube.com/vi/MDNRyXL20_Y/0.jpg)](https://youtu.be/MDNRyXL20_Y)

---

## 🔥 트러블 슈팅

---

### 🚨 Issue 1
### 🚧 InputMismatchException

#### 💭 [이슈 내역]

프로그램 실행 > 로그인 > 근태조회 > 직원 > 특정 > 사번 검색 > 문자열 입력 시 예외발생

#### 🛑 [원인]

아래는 사번으로 직원의 근태를 조회하는 코드이다.
```java
	System.out.print("(직원정보)사번 입력 : ");
	int memberId = Container.getSc().nextInt();
	Container.getSc().nextLine();
	Member member = stateService.memberFindById(memberId);
```
```int```형으로 선언된 ```memberId``` 변수에 ```문자열(String)```을 입력하면 자료형이 달라 변수에 대입할 수 없기 때문에 예외가 발생한다.

#### 🚥 [해결]
해당 코드를 ```try```문으로 감싸고 ```catch(InputMismatchException e)```를 생성하여 예외상황에 대한 알림을 띄워준 후 ```return``` 시켜 프로그램 초기 동작으로 돌아가게 만들었다.

---

### 🚨 Issue 2
### 🚧 javax.mail.internet.AddressException

#### 💭 [이슈 내역]

프로그램 실행 > 로그인 > 메일발송 > 이메일 주소 오입력 시 예외 발생
![](https://velog.velcdn.com/images/asdf4321/post/049cb1f4-54bb-4240-84da-c21a96d7d7f0/image.png)


#### 🛑 원인
1. 수신 이메일 주소가 올바르지 못함

#### 🚥 해결
이메일을 발송하는 코드에 이미 예외상황에 대한 ```catch```문이 존재하였으나 예외상황에 대한 ```printStackTrace()```메서드를 실행시키지 않고, 사용자가 알아볼 수 있도록 알림을 나타내도록 바꾸고 프로그램 초기 동작으로 돌아가게 수정함.
- 수정전 코드
```java
catch (MessagingException e) {
            e.printStackTrace();
        }
```
- 수정후 코드
```java
catch (MessagingException e) {
            System.out.println("<알림> 이메일 주소가 올바르지 않습니다.");
        }
```

- 코드 수정 후
![](https://velog.velcdn.com/images/asdf4321/post/07f8bc6e-0184-4b98-bd31-2423a4bf9286/image.png)

---

### 🚨 Issue 3
### 🚧 javax.mail.internet.AddressException

#### 💭 [이슈 내역]

프로그램 실행 > ID찾기 > 이메일 발송 실패 알림 생성 후 프로그램 로직 멈추지 않음
![](https://velog.velcdn.com/images/asdf4321/post/40818471-fe05-40da-bcf9-28a3cbd8ab1a/image.png)


#### 🛑 원인
1. SendMail.naverMailSend()의 메서드가 void기 때문에 반환하는 값이 없어 조건에 제약을 걸어 로직을 멈출 수 없다.

#### 🚥 해결
1. SendMail.naverMailSend() 메서드를 String값을 반환하도록 변경하고 이메일 발송에 성공했을 경우, "성공"을 반환시키고, 예외가 발생하여 실패하게 되면 알림을 출력하고 "실패"를 반환하도록 수정함

- 수정전 코드
1. SendMail
```java
public static void naverMailSend(String emailAddress) {
	try {
    	...이메일 발송 로직
    } catch {
    	...이메일 발송 실패 알림 출력
    }
}

```
2. MemberController
```java
        SendMail.naverMailSend(member.getEmail());

        System.out.println("<알림> 등록하신 이메일 주소로 보안코드를 발송하였습니다.");
```

- 수정후 코드
1. SendMail
```java
    public static String naverMailSend(String emailAddress) {
    	try{
        	...이메일 발송 로직
            return "성공";
        } catch {
        	...이메일 발송 실패 알림 출력
            return "실패";
        }
    }
```
2.MemberController
```java
        String sendEmail = SendMail.naverMailSend(member.getEmail());
        if (sendEmail.equals("실패")) {
            return;
        }
```
- 수정 후 프로그램 로직
![](https://velog.velcdn.com/images/asdf4321/post/d64c104c-7678-459d-9bc9-853eb4a0b150/image.png)
진행되던 로직은 중단되고 초기 동작으로 돌아간다.
