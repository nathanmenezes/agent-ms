package br.com.meinz.agentms.service.function;

import br.com.meinz.agentms.enums.FunctionType;

public interface FunctionStrategy<INPUT, OUTPUT> {
     OUTPUT execute(INPUT input);

     FunctionType functionType();
}
