# IssueToJira 


Java приложение (maven проект, spring boot), создания задач в Jira на основе исходных данных в XML файле.

Для запуска Приложения, выполнить команду:

```shell
mvn spring-boot:run
```

При локальном запуске, Приложение доступно по адресу [http://localhost:7070](http://localhost:7070). При переходе 
по указанному адресу открывается страница "Форма загрузки задач в Jira":

![form.png](form.png)

Приложение не создает проект, оно находит его в Jira по projectKey, указанному в XML файле, см. ниже.
Т.е. требуется предварительно создать проект с тем же ключом в Jira.

XML файл содержит информацию (имя, приоритет и тип) о задачах в проекте,
которые нужно создать, имеет следующую структуру:

```xml
<data>
    <projectKey>PR</projectKey>
    <issues>
        <issue>
            <id>1</id>
            <type>task</type>
            <priority>Highest</priority>
        </issue>
        <issue>
            <id>2</id>
            <type>task</type>
            <priority>Highest</priority>
        </issue>
        <issue>
            <id>3</id>
            <type>task</type>
            <priority>Highest</priority>
        </issue>
    </issues>
    <descriptions>
        <description>
            <id>1</id>
            <summary>Task case 1</summary>
        </description>
        <description>
            <id>2</id>
            <summary>Task case 2</summary>
        </description>
        <description>
            <id>3</id>
            <summary>Task case 3</summary>
        </description>
    </descriptions>
</data>
```

*XML файл имеет следующие особенности, которые необходимо учитывать:*

* элементы summary находятся в отдельном теге, и связаны с тегом issue через id
* тегом projectKey задается ключ проекта