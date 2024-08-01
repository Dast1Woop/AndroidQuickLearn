fun main(args: Array<String>) {
//    println("Hello World11!")
//
//    var name = "lilei"
//    var name1 = "hmm"
//    var sex = 0
//    sex = 1
//    println(name)
//    println(name1)
//    println("my 名字 is $name")
//
//    var number = 10.0
//    println(number::class.simpleName)
//
//    var strOpt: String? = "hihihi"
//    strOpt?.let {
//        println("let is ${it.length} chars")
//    } ?: run {
//        println("strOpt is null!!")
//    }
//
//    strOpt?.run {
//        println("this is $this")
//    }
//
//    val x = 0
//    val y = 20
//    println("x+y=${x+y}")
//
//    when (x) {
//        in 1 .. 10 -> println("morning")
//        11 -> println("11点了")
//        else -> println("when not match")
//    }

//    sayHi()
//    say("hello")
//    println("sum:${add(10,20)}")
//    println(isBigger(10,81))
//
//    println(sum(1,2,3))

//  outerLoop@ for (i in 1..5) {
//        for (j in 1..3) {
//            if (i==2) {
//                break@outerLoop
//            }
//            println("$i-$j")
//        }
//    }

//    val a = arrayOf(1,2,3)
////    a.add
//    a[0] = 8
//    println(a[0])
//    println(a.toString())

//    val intA = intArrayOf(6,9,3,2,4)
////    intA.add
//    intA[0] = 0
//    println(intA.joinToString())
//    println(intA.joinToString(limit = 2))

//    var al = arrayListOf<Int>
//    al.add(3)
//    al[0] = 6
//    println(al)

//    val list = listOf<Int>(1, 2, 3)
////    list[0] = 4
//    println(list)
//
//    val listMt = mutableListOf<Int>(4,5,6)
//    listMt[0] = 8
//    listMt.add(9)
//    println(listMt)

//    val person1 = Person("lilei",18,true, grade = 6)
//    person1.nation = "汉"
//    println(person1.des())
//
//    val p2 = Person("hmm",20)
//    println(p2.des())
//
//    var car1 = Car("myl","byd",4,999999)
//    println(car1.toString())
//
//    var car2 = Car("my car","bmw")
//    println(car2.toString())
//
//    var carBenci = Car("da ben")
//    println(carBenci.toString())

//    val cal = Calculator()
//    cal.type = "得力"
//    println("pi is ${Calculator.pi}")
//    println("3 + 4 = ${Calculator.addCustom(3,4)}")
//
//    cal.priceCustom = "100~200"
//    println(cal.priceCustom)

    val car = Car("AoDi")
    car.move()
    car.fly()

    val calcu = { a:Int, b:Int ->
        if (a>b) {
            a
        }else {
            b
        }
    }
    println(calcu(4,5))
    println(calcu(14,56))

    val arr = arrayOf(1,2,3)
    arr.forEach {
        println(it * it)
    }

    arr.forEach { x ->
        println(x + 1)
    }

    val dic:Map<String,Int> = ("1" to 1, "2" to 2)

}

abstract class Vehicle {
    abstract fun move()
}

interface CanFly {
    fun fly()
}

class Calculator {
    companion object {
        val pi = 3.14
        fun addCustom(a:Int, b:Int):Int {
            return a + b
        }
    }

    var type:String? = null

    //lateinit 只能用于类类型（class type）或者引用类型（如 String）
    lateinit var priceCustom:String
}

class Person(var name:String, var age:Int, var ismale:Boolean, grade:Int) {
    var nation:String? = null
    var grade:Int?
    init {
        if (grade < 10) {
            this.grade = grade
        }else {
            this.grade = null
        }

    }

    //使用其他构造方法时，使用this调用，不能用Person
    constructor(name: String, age: Int): this(name,age,ismale=false,grade=1)
    fun des() {
        println("hi,my name is $name, age:$age, ismale:$ismale,nation:$nation, grade:$grade")
    }
}

class Car:Vehicle, CanFly {
    constructor(name: String?, type: String?, wheels: Int?, id:Int?) {
        this.name = name
        this.type = type
        this.wheels = wheels
        this.id = id
    }

    constructor(name: String?, type: String?) {
        this.name = name
        this.type = type
    }

    constructor(name: String?):this(name, type = "benCi")

    var name:String?
    var type:String?
    var wheels:Int? = 4
    var id:Int? = null
    override fun move() {
//        TODO("Not yet implemented")
        println("car is moving!")
    }

    override fun fly() {
//        TODO("Not yet implemented")
        println("car can fly!!!")
    }


    override fun toString(): String {
        return "Car(name=$name, type=$type, wheels=$wheels, id=$id)"
    }


}

fun sayHi() {
    println("hi")
}

fun say(some:String) {
    println("say:$some")
}

fun add(num1:Int, num2:Int):Int {
   return num1 + num2
}

fun isBigger(x:Int, y:Int):Boolean {
    return (x > y)
}

fun sum(vararg items:Int):Int {
    var sum = 0
    for (i in  items) {
        sum += i
    }
    return  sum
}