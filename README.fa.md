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

## 1️⃣ چرا UUID به‌عنوان Primary Key؟

### 🔹 UUID چیست؟
یک شناسه ۱۲۸ بیتی یکتا در سطح جهانی (Globally Unique)
```java
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID accountId;

```

### ✅ مزایای UUID
✔ یکتا در کل سیستم (نه فقط یک جدول)

✔ مناسب Microservices

✔ امن‌تر از ID ترتیبی (حدس‌زدنی نیست)

✔ امکان تولید در Client یا Service بدون DB roundtrip

### ❌ معایب UUID
❌ Index بزرگ‌تر
❌ Performance کمی ضعیف‌تر نسبت به Long
❌ خوانایی کمتر

### 🟡 چه زمانی UUID؟
| سناریو             | انتخاب |
| ------------------ | ------ |
| Microservices      | ✅ UUID |
| Distributed System | ✅ UUID |
| Monolith ساده      | ❌ Long |
| Public API         | ✅ UUID |

### 🎯 جمله مصاحبه‌ای
EN:
We use UUID as a primary key to guarantee global uniqueness, especially in distributed or microservice-based systems.

DE:
UUIDs werden verwendet, um eine globale Eindeutigkeit sicherzustellen, insbesondere in verteilten Systemen.