# Notes

This tool kinda works. It has not been tested that well. Time, as always, is the
reason. This is a teaching tool to teach students about SOAP and REST based 
webservices. It's the first round and will probably be tidied up a lot next 
time it's in use.

Some things that I know that there are problems with

 * Some JSON output seems to have extra commas in it
 * Add in a MVC controller with thymeleaf to show what a GUI could look like
 * Add in a SOAP cheat controller to be able to show SOAP messages without SoapUI
 * Make all REST GET examples work in browser, seems to be content-type problem
 * Replace hateoas link generation with springs version
 * Get WADL and swagger working. WADL seems to be missing grammers. Probably 
 generate manually. Tried java2wsdl, but didn't have time to figure it out
 * Create junit tests 
