package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var shift int

func main() {
	reader := bufio.NewReader(os.Stdin)
	text, _ := reader.ReadString('\n')
	size := 16
	//programs := []string{"a", "b", "c", "d", "e"}
	memory := make(map[string]bool)
	programs := []string{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"}
	hash := make(map[string]int, size)
	for i, p := range programs {
		hash[p] = i
	}
	operations := strings.Split(text, ",")
	// fmt.Println(text[len(text)-1])
	start := 0
	for i := 0; i < 1000000000; i++ {
		for _, op := range operations {
			switch op[0] {
			case 's':
				//fmt.Println(programs)
				var temp string = op[1:]
				// fmt.Println(temp)

				start = spin(temp, start, size)

				//fmt.Println(start)
				//fmt.Println(programs)

			case 'x':
				temp := op[1:]
				//fmt.Println(programs)
				programs = swap(temp, programs, hash, start, size)
				//fmt.Println(programs)

			case 'p':
				temp := op[1:]

				programs = partner(temp, programs, hash, start, size)
				//fmt.Println(programs)

			}
		}
		if _, ok := memory[strings.Join(programs, ",")]; ok {
			numCicle := 1000000000 / i
			i = numCicle * i
		} else {
			memory[strings.Join(programs, ",")] = true
		}
	}
	fmt.Println(programs)
	// fmt.Println(start)

	for i := 0; i < size; i++ {
		fmt.Print(programs[(start+i)%size])
	}
	fmt.Println()
}

func spin(op string, start int, size int) int {
	valOp, _ := strconv.Atoi(op)

	temp := start - valOp
	if temp < 0 {
		temp = size - (temp * -1)
	}
	return temp

}
func swap(op string, programs []string, hash map[string]int, start int, size int) []string {
	res := strings.Split(op, "/")
	pos1, _ := strconv.Atoi(res[0])
	pos2, _ := strconv.Atoi(res[1])
	pos1 = (start + pos1) % size
	pos2 = (start + pos2) % size
	// fmt.Println(start)
	// fmt.Println(pos1)
	// fmt.Println(pos2)
	temp := programs[pos1]
	hash[programs[pos1]] = pos2
	hash[programs[pos2]] = pos1

	programs[pos1] = programs[pos2]
	programs[pos2] = temp

	return programs
}
func partner(op string, programs []string, hash map[string]int, start int, size int) []string {
	res := strings.Split(op, "/")

	//fmt.Println(programs)
	program1 := res[0]
	program2 := res[1]
	pos1 := (hash[string(program1)])
	// fmt.Println(pos1)
	pos2 := (hash[string(program2)])
	// fmt.Println(pos2)
	temp := programs[pos1]
	// fmt.Println(temp)
	programs[pos1] = programs[pos2]
	programs[pos2] = temp
	hash[string(programs[pos1])] = pos1
	hash[string(programs[pos2])] = pos2

	return programs

}
