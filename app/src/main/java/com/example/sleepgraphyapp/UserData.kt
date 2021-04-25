package com.example.sleepgraphyapp

class UserData {
    var Name: String? = null
    var Age: String? = null
    var Gender: String? = null
    var Email: String? = null

    constructor() {}

    constructor(name: String?, age: String?, gender: String?, email: String?) {
        Name = name
        Age = age
        Gender = gender
        Email = email
    }
}