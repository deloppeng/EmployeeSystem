# 部門及員工CRUD

## 使用框架：
- Spring boot 2.3.4.RELEASE
- Hibernate
- Spring data jpa
- Mockito

## DB:
- H2 1.4.196

## 功能介紹：
### 新增員工資料 <br />
- Api: /employee <br />
- method: POST <br />
- 輸入欄位： <br />
  - name 姓名 <br />
  - age 年齡 <br />
  - gender 性別 <br />
  - department_id 部門id <br />
  - eid 員工編號 <br />
  - phone 電話 <br />
  - address 地址 <br />
<br />
成功post後會自動建立 員工表中的 id, 建立時間 及 最後一次修改時間。<br />

---

### 更新員工資料 <br />
- Api: /employee/{id} <br />
- method: PUT <br />
- 輸入欄位： <br />
  - name 姓名 <br />
  - age 年齡 <br />
  - gender 性別 <br />
  - department_id 部門id <br />
  - eid 員工編號 <br />
  - phone 電話 <br />
  - address 地址 <br />
<br />
請求成功後會更新員工表中的該id中PUT的欄位資料，最後一次修改時間會自動更新。<br />

---

### 刪除員工資料 <br />
- Api: /employee/{id} <br />
- method: DELETE <br />
<br />
請求成功後會刪除員工表中的該id中的資料，回傳"Deleted {id}"的json 字串。<br />

---

### 新增部門資料 <br />
- Api: /department/ <br />
- method: POST <br />
<br />
- 輸入欄位： <br />
  - name 部門名稱 <br />
成功post後會自動建立 部門表中的 id。<br />

---

### 更新部門資料 <br />
- Api: /department/{id} <br />
- method: PUT <br />
<br />
- 輸入欄位： <br />
  - name 部門名稱 <br />
請求成功後會更新部門表中的該id中PUT的欄位資料。<br />

---

### 刪除部門資料 <br />
- Api: /department/{id} <br />
- method: DELETE <br />
<br />
請求成功後會刪除員工表中的該id中的資料，回傳""Delete Department:{id}""的json 字串。<br />

---

### 查詢員工資料 <br />
- Api: /department/{id} <br />
- method: DELETE <br />
- 輸入欄位： <br />
  - name 姓名 <br />
  - age 年齡 <br />
  - gender 性別 <br />
  - department_id 部門id <br />
  - eid 員工編號 <br />
  - phone 電話 <br />
  - address 地址 <br />
  - department.name 部門名稱 <br />
  - sortType 排序方式 <br />
  - sortColumn 排序欄位 <br />
  - size 每頁顯示筆數 <br />
  - page 第幾頁 <br />
<br />

請求成功後會顯示所搜尋條件出來的資料, sortColumn為欄位名, sortType為ASC或DESC  <br />

