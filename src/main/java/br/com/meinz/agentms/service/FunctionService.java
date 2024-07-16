package br.com.meinz.agentms.service;

import br.com.meinz.agentms.repository.FunctionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FunctionService {

    private final FunctionRepository functionRepository;

}
