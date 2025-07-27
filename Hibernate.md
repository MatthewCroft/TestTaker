# Hibernate
notes to learn hibernate

## Lifecycle
There are four main states in hibernates lifecycle. Transient,
Persistent, Detached, Removed. 

### Transient
* The entity is not associated with a hibernate session
* It is a regular POJO
* It does not exist in the database context yet and has no persistence context
```java
ExamUser user = new ExamUser(); // transient
```
### Persistent
* The entity is associated with a Hibernate Session.
* Hibernate tracks changes to the object and will synchronize with the database on flush or commit.
* Becomes persistent when you:
  * call session.save(), session.persist()
  * retrieve and entity using session.get(), or session.find()

```java
Session session = sessionFactory.openSession();
session.beginTransaction();
session.save(user); // becomes persistent
session.getTransaction().commit();
```
### Detached
* The entity was once persistent, but the session is now closed.
* Changes made to it will not be automatically tracked.
* You can reattach it using session.update(), session.merge()

```java
session.close(); // now detached
user.setName("Updated name");
```

### Removed
* The entity is marked for deletion from the database
* Happens when you call session.delete(), or entityManager.remove();
* The record is deleted on flush/commit

```java
session.delete(user); //becomes removed
```