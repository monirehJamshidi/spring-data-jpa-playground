# spring-data-jpa-playground

[🇮🇷  ENGLISH_ ](./README.md)

### ⭐ سطح پروژه
#### ✅ Mid → Senior Level

از نظر:
- Repository patterns
- Query methods
- Transaction
- Config

### ⭐ اگر این پروژه را در مصاحبه توضیح بدهی:
حتماً بگو:
- چرا UUID
- چرا open-in-view=false
- تفاوت grammar vs @Query
- REST method semantics (GET vs POST)

### 1️⃣ چرا UUID به‌عنوان Primary Key؟

#### 🔹 UUID چیست؟
یک شناسه ۱۲۸ بیتی یکتا در سطح جهانی (Globally Unique)
```java
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID accountId;

```

#### ✅ مزایای UUID
✔ یکتا در کل سیستم (نه فقط یک جدول)

✔ مناسب Microservices

✔ امن‌تر از ID ترتیبی (حدس‌زدنی نیست)

✔ امکان تولید در Client یا Service بدون DB roundtrip

#### ❌ معایب UUID
❌ اول Index بزرگ‌تر

❌ و بعد Performance کمی ضعیف‌تر نسبت به Long

❌ خوانایی کمتر

#### 🟡 چه زمانی UUID؟
| سناریو             | انتخاب |
| ------------------ | ------ |
| Microservices      | ✅ UUID |
| Distributed System | ✅ UUID |
| Monolith ساده      | ❌ Long |
| Public API         | ✅ UUID |

#### 🎯 جمله مصاحبه‌ای
<hr/>
EN:
We use UUID as a primary key to guarantee global uniqueness, especially in distributed or microservice-based systems.
<hr/>
DE:
UUIDs werden verwendet, um eine globale Eindeutigkeit sicherzustellen, insbesondere in verteilten Systemen.

<hr/>

### 2️⃣ چرا open-in-view=false ؟
### 🔹 Open Session in View (OSIV) چیست؟
به‌طور پیش‌فرض:
- در حقیقت Hibernate Session تا پایان Response باز می‌ماند
- حتی داخل Controller

#### ❌ مشکل OSIV = true
- در حقیقت Query در Controller اجرا می‌شود 😱
- و N+1 Problem پنهان می‌ماند
- و  Transaction boundary نامشخص
- و Lazy Loading خارج از Service

#### مزایای ✅ open-in-view=false

```yaml
spring:
  jpa:
    open-in-view: false

```

✔ دیتابیس فقط در Service Layer

✔ در حقیقت LazyInitializationException زود دیده می‌شود (خوبه!)

✔ معماری تمیز

✔ قابل تست‌تر

#### 🎯 جمله مصاحبه‌ای
EN:
Disabling Open Session in View enforces proper transaction boundaries and prevents accidental database access in the controller layer.

DE:
Durch das Deaktivieren von Open Session in View werden saubere Transaktionsgrenzen erzwungen.

<hr/>


### 3️⃣ تفاوت Grammar Methods vs @Query
#### 🔹 Grammar Methods (Derived Queries)
Spring Data از روی اسم متد Query می‌سازد.
```java
List<Account> findByAccountBalanceLessThan(Long balance);

```

##### ✅ مزایا
✔ بدون JPQL

✔ خوانا

✔ سریع برای CRUD ساده

✔ و Database-agnostic

##### ❌ معایب
❌ برای Queryهای پیچیده طولانی می‌شود

❌ برای Joinهای پیچیده سخت

#### 🔹 @Query (Programmatic)
Query را خودت می‌نویسی.
```java
@Query("select a from Account a where a.accountBalance > :min")
List<Account> getAccounts(@Param("min") Long min);

```

#####✅ مزایا
❌ وابسته به Query

❌ احتمال خطای Runtime

❌ خوانایی کمتر

#### 🎯 جمله مصاحبه‌ای
#### EN:
Derived queries are preferred for simple lookups, while @Query is used when more control or complex joins are required.

#### DE:
Abgeleitete Queries eignen sich für einfache Abfragen, während @Query für komplexe Queries verwendet wird.


