package org.example.testtaker.model;

public class Test {
    Integer testId;
    String name;
    String createdBy;

    public Test(Integer testId, String name, String createdBy) {
        this.testId = testId;
        this.name = name;
        this.createdBy = createdBy;
    }
     public Test() {}

    public Integer getTestId() {
        return testId;
    }

    public String getName() {
        return name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        Test test = (Test) o;
        return this.getName().equals(test.getName()) &
                this.getCreatedBy().equals(test.getCreatedBy());
    }
}
