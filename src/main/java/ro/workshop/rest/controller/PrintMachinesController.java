package ro.workshop.rest.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.workshop.core.repository.PrintMachineRepository;
import ro.workshop.rest.domain.PrintMachine;
import ro.workshop.util.Functions;

import java.util.List;

@Controller
@RequestMapping("/printmachines")
public class PrintMachinesController {

    @Autowired
    private PrintMachineRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PrintMachine> getAllPrintMachines() {
        return ImmutableList.copyOf(Iterables.transform(repository.findAll(), Functions.toRest.printMachine()));
    }
}
