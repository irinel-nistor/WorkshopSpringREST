This workshop demonstrates how to use Spring REST web services using AJAX (hopefully)

The business modeled by the application is an online printing service, which allows users to create orders,
cancel them, view their printStatus and find out which machines are free to do the printing.
Therefore, will have to create the following entities: PrintOrder, PrintStatus, OrderDetails, PrintMachine.

Order Status:
New -> Waiting -> Printing -> Done

Application should display a form where user can create an order by filling in color, size and message for t-shirt to print.