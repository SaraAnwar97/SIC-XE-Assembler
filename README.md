# SIC-XE-Assembler

The project is to implement SIC/XE assembler that produces code for the absolute loader
used in the SIC/XE programming assignments.

In phase 1 of the project, it is required to implement Pass1 of the assembler. The output of this
phase should be used as input for subsequent phase.

In phase 2 of the project, We are going to build on the previous phase and use its output to
implement pass 2 of the assembler.

# Prerequisites

install NetBeans IDE.

# Phase 1 Specifications

1. Built a parser that is capable of handling source lines that are instructions, storage
declaration, comments, and assembler directives (a directive that is not implemented should be
ignored possibly with a warning).

2. For instructions, the parser is to minimally be capable of decoding 2, 3 and 4-byte instructions as
follows:

a) 2-byte with 1 or 2 symbolic register reference (e.g., TIXR A, ADDR S,A)

b) RSUB (ignoring any operand or perhaps issuing a warning).

c) 3-byte PC-relative with symbolic operand to include immediate, indirect, and indexed
addressing.

d) 3-byte absolute with non-symbolic operand to include immediate, indirect, and indexed
addressing.

e) 4-byte absolute with symbolic or non-symbolic operand to include immediate, indirect,
and indexed addressing.

3. The parser is to handle all storage directives (BYTE, WORD, RESW, and RESB).

4. The output of this phase should contain (at least):

a) The symbol table.

b) The source program in a format similar to the listing file.

c) A meaningful error message should be printed below the line in which the error
occurred.

# Phase 2 Specifications

1. The output of the assembler should include:

a) Object-code file.

2. The assembler should support:

a) EQU and ORG statements.

b) Simple expression evaluation. A simple expression includes simple (A <op> B) operand
arithmetic, where <op> is one of +, -, *, / and no spaces surround the operation, eg. A+B.
  
  # Main data structutes
  
  -Array List
  
  -Hash Map
  
  # Note

You must enter a Starting address.

