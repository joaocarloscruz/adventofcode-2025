import re
import sys
from itertools import combinations

def part1():
    try:
        with open("day10/part1/input.txt", "r") as f:
            lines = f.readlines()
    except FileNotFoundError:
        print("Error: 'input.txt' not found.")
        return

    total_presses = 0

    for line in lines:
        line = line.strip()
        if not line:
            continue

        diagram_match = re.search(r'\[([.#]+)\]', line)
        buttons_match = re.findall(r'\(([\d,]+)\)', line)

        if not diagram_match:
            continue

        diagram_str = diagram_match.group(1)
        target = 0
        for i, char in enumerate(diagram_str):
            if char == '#':
                target |= (1 << i)

        buttons = []
        for b_str in buttons_match:
            mask = 0
            indices = [int(x) for x in b_str.split(',')]
            for idx in indices:
                mask |= (1 << idx)
            buttons.append(mask)

        found = False
        if target == 0:
            current_min = 0
            found = True
        else:
            for r in range(1, len(buttons) + 1):
                for combo in combinations(buttons, r):
                    state = 0
                    for b_mask in combo:
                        state ^= b_mask
                    
                    if state == target:
                        current_min = r
                        found = True
                        break
                if found:
                    break
        
        if found:
            total_presses += current_min

    print(total_presses)

if __name__ == "__main__":
    part1()