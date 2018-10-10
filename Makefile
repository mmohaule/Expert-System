# **************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: thmotaun <marvin@42.fr>                    +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2017/10/29 15:39:06 by thmotaun          #+#    #+#              #
#    Updated: 2017/10/29 15:40:45 by thmotaun         ###   ########.fr        #
#                                                                              #
# **************************************************************************** #

JAVA_SRCS		:=$(wildcard *.java)
JAVA_CLASSES	= $(subst /src/,/build/,$(JAVA_SRCS:.java=.class))
JFLAGS			= -g
JC				= javac
MAIN			= expert_system
JVM				= java

define colourecho
tput blink
tput setaf 6
@echo $1
tput sgr0
endef

define colourecho1
tput setaf 6
@echo $1
tput sgr0
endef

define colourecho2
tput setaf 87
@echo $1
tput sgr0
endef

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

default: build

build: $(JAVA_CLASSES)

run_1:
	@$(call colourecho1, "Running...")
	$(JVM) $(MAIN) ./tests/ex1.txt
run_2:
	@$(call colourecho1, "Running...")
	$(JVM) $(MAIN) ./tests/ex2.txt
run_3:
	@$(call colourecho1, "Running...")
	$(JVM) $(MAIN) ./tests/ex3.txt
run_4:
	@$(call colourecho1, "Running...")
	$(JVM) $(MAIN) ./tests/ex4.txt
run_5:
	@$(call colourecho1, "Running...")
	$(JVM) $(MAIN) ./tests/ex5.txt
run_6:
	@$(call colourecho1, "Running...")
	$(JVM) $(MAIN) ./tests/ex6.txt
run_7:
	@$(call colourecho1, "Running...")
	$(JVM) $(MAIN) ./tests/test.txt
run_8:
	@$(call colourecho1, "Running...")
	$(JVM) $(MAIN) ./tests/test1.txt

clean:
	$(RM) *.class