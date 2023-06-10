
import { Component } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Component({
  selector: 'calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent {
  constructor(private http:HttpClient){

  };
  url="http://localhost:9091";
 expression='';
 clearAll(){
  this.expression='';
 }
 showOn(num:String){
  this.expression=this.expression+num;
 }
 reciprocal(x:string){
  this.clearAll();
  this.expression="1"+"/"+x;
 }
 square(x:string){
  this.clearAll();
  this.expression=x+"^"+"2";
 }
 undo(){
  this.expression= this.expression.slice(0, this.expression.length - 1);

 }
 evaluate(){
  this.http.post(this.url,this.expression,{responseType:'text'}).subscribe((result)=>{
    this.expression=result;
  });
  }

}
