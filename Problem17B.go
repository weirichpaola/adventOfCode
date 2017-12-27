package main

import (
	"fmt"
)

func main() {
	num := 314
	size := 1
	currPos := 0
	res := 0
	for i := 1; i < 50000000; i++ {
		pos := (num + currPos) % size
		currPos = pos + 1
		size++
		if pos == 0 {
			res = i
		}
	}

	fmt.Println(res)

}
