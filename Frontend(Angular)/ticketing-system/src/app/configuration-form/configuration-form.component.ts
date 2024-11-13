
import { config } from './../app.config.server';
import { Component,output ,EventEmitter, Output} from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-configuration-form',
  standalone:true,
  imports:[FormsModule],
  templateUrl: './configuration-form.component.html',
  styleUrls: ['./configuration-form.component.css']
})
export class ConfigurationFormComponent {

  config={
    totalTickets:0,
    ticketReleaseRate:0,
    customerRetrievalRate:0,
    maxTicketCapacity:0
  };


  @Output() configSubmit=new EventEmitter<any>();

  submitForm(){
    this.configSubmit.emit(this.config)
  }


}
