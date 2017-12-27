package main

import (
	"container/list"
	"fmt"
)

func main() {
	num := 314
	list := list.New()
	_ = list.PushFront(0)
	//list.InsertAfter(nil, e0)
	size := 1
	currPos := 0
	for i := 1; i < 2018; i++ {
		pos := (num + currPos) % size
		currPos = pos + 1
		size++
		e := list.Front()
		for j := 0; j < pos; j++ {
			e = e.Next()
		}
		elem := list.InsertAfter(i, e)
		var res int
		if elem.Value == 2017 {
			res = elem.Next().Value.(int)
		}
		fmt.Printf("answer %d", res)
	}

}
