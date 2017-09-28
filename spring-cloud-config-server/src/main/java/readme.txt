一、访问http://localhost:8888/person-dev.properties

person.age: 18
person.email: xs1@gamil.com
person.name: xiaoming

环境信息:
****************************
name=person
profiles=dev
label=master
********************************

二、访问规则

[/{name}-{profiles}.properties]
[/{name}-{profiles}.yml || /{name}-{profiles}.yaml]
[/{name}-{profiles}.json]
[/{label}/{name}-{profiles}.yml || /{label}/{name}-{profiles}.yaml]
[/{name}/{profiles}/{label:.*}]
[/{name}/{profiles:.*[^-].*}]
[/{label}/{name}-{profiles}.properties]
[/{label}/{name}-{profiles}.json]
[/{name}/{profile}/{label}/**]
[/{name}/{profile}/{label}/**]