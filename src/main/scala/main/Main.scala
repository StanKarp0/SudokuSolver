package main

/**
  * Created by wojciech on 18.02.17.
  */
object Main extends App {

  val sudoku: List[Int] =  List(
    5,0,0, 3,0,0, 0,4,0,
    0,0,0, 5,9,0, 0,0,6,
    7,0,8, 0,2,4, 0,9,0,

    3,0,0, 0,0,0, 0,0,0,
    0,5,0, 6,0,0, 0,1,0,
    9,0,0, 0,0,0, 0,0,0,

    2,0,9, 0,3,1, 0,5,0,
    0,0,0, 9,8,0, 0,0,3,
    4,0,0, 7,0,0, 0,2,0
  )

  RecSolver.solve(sudoku).foreach{
    _.sliding(9, 9).foreach(println)
  }


}
