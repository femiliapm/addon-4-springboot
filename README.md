# Product API

## Structure Project

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── aocfazz
│   │           └── productAPI
│   │               ├── config
│   │               │   └── AuthFilter.java
│   │               ├── controller
│   │               │   └── GreetingController.java
│   │               ├── exception
│   │               ├── model
│   │               ├── payload
│   │               │   ├── req
│   │               │   └── res
│   │               ├── repository
│   │               ├── service
│   │               └── ProductApiApplication.java
│   └── resources
│       ├── static
│       ├── templates
│       └── application.properties
└── test
```

## How to Run
`.\mvnw spring-boot:run`