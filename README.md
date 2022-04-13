# WeChat JS_CODE to Session Server in Java

N.J <congtao.jiang@outlook.com>
April, 2022

## Introduction

A restful server that can retrieve WeChat user session information for a given WeChat wxlogin code (js_code).

WeChat user session includes
- session_key
- openid
- unionid

## Start Application

```sh
mvn spring-boot:run
```

## Testing

```sh
curl -X POST http://localhost:3000/api/v1/wxlink -H "Content-Type: application/json"  --data '{"wxcode": "123124"}' -v
```

## Application Configuration

`application.properties`:

```
wx.mp.app.id=<Your WeChat Mini-Program application id>
wx.mp.app.secret=<Your WeChat Mini-Program application secret>
wx.api.jscode2session=https://api.weixin.qq.com/sns/jscode2session
```

## Run using your application properties

```
 mvn spring-boot:run  -Dspring.config.location=/<your-path>/application.properties
```

## Build a docker images

```shell
docker build -t jscode2session .
```

### Run docker image

```shell
 docker run --rm  -p3000:3000 jscode2session    
```

test

```shell
curl -X POST http://localhost:3000/api/v1/wxlink -H "Content-Type: application/json"  --data '{"wxcode": "123124"}' -v
```

## Get pre-built docker images

```shell
docker pull congtaojiang/jscode2session:latest
``` 

### Run pre-built docker images

```shell
docker run --rm  -p3000:3000 congtaojiang/jscode2session:latest
```

test

```shell
curl -X POST http://localhost:3000/api/v1/wxlink -H "Content-Type: application/json"  --data '{"wxcode": "123124"}' -v
```