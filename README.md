# Sample Spring Boot Application with H2, Java Record, JDBCTemplate

<li>Java Version: 18</li>
<li>Spring Boot Version: 3.2.0-M3</li>
<li>Database : H2</li>
<br>
<b>Summary</b> :
<p>This is a sample spring boot application to serve as a sinple CRUD(Create, Read, Update, Delete) application.
<br>
With the help of exposed apis we can create a new droid, update its model, show all droids registered, delete a droid.</p>

<b><h3>Exposed APIs:</h3></br>
<table>
    <tr>
        <th>API</th>
        <th>Description</th>
        <th>Input type</th>
        <th>Input</th>
    </tr>
    <tr>
        <td>/api/createDroid</td>
        <td>Create a new Droid with name and model</td>
        <td>RequestBody</td>
        <td>Droid droid</td>
    </tr>
    <tr>
        <td>/api/showAllDroids</td>
        <td>Display all droids</td>
        <td>None</td>
        <td>None</td>
    </tr>
    <tr>
        <td>/api/editDroid</td>
        <td>Update a droid's model based on name</td>
        <td>RequestBody</td>
        <td>Droid droid</td>
    </tr>
    <tr>
        <td>/api/deleteDroid</td>
        <td>Delete a droid</td>
        <td>RequestParam</td>
        <td>name</td>
    </tr>
</table>
---
<br><i>Please refer to com.self.probe.model.Droid.java to find an implementation of a fairly new feature of Java, i.e, record.</i>
<br>
---
Feel free to clone and experiment yourselves too.

