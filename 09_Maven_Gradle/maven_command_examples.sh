#!/usr/bin/env bash
mvn test -Ptestcases
mvn test -Papi
mvn test -Pui
