package main

/**
  * Created by wojciech on 18.02.17.
  */
object RecSolver {

  private val SudokuSize = 9
  private val SquareSize = 3

  private def square(x: Int, y: Int) =
    (y/SquareSize)*SquareSize + x/SquareSize

  private val squares =
    List.range(0,SudokuSize * SudokuSize)
      .groupBy(d => square(d%SudokuSize,d/SudokuSize))
      .compose[(Int,Int)]{case(x,y) => square(x, y)}

  private val lists = List.tabulate(SudokuSize, SudokuSize)((y, x) => {
    List.tabulate(SudokuSize)(_ * SudokuSize + x) :::
      List.tabulate(SudokuSize)(y * SudokuSize + _) :::
      squares(x,y)
  }).flatten.map(_.distinct)

  def solve(a: List[Int]): Option[List[Int]] = {

    def canPut(a: List[Int], i: Int, v: Int): Boolean = !lists(i).map(a(_)).contains(v)

    def rec(a: List[Int], i: Int): List[List[Int]] = a.lift(i) match {
      case Some(v) if v != 0 && i == a.size-1 => List(a)
      case Some(v) if v != 0 => rec(a, i+1)
      case _ if i == a.size - 1 => List.range(1, 10).filter(canPut(a, i, _)).map(a.updated(i, _))
      case _  => List.range(1, 10).filter(canPut(a, i, _)).flatMap(x => rec(a.updated(i, x), i+1))
    }

    rec(a, 0).headOption
  }



}
