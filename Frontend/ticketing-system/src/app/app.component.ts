import { config } from './app.config.server';
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfigurationFormComponent } from './configuration-form/configuration-form.component';
import { TicketDisplayComponent } from './ticket-display/ticket-display.component';
import { ControlPanelComponent } from './control-panel/control-panel.component';
import { LogDisplayComponent } from './log-display/log-display.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule,ConfigurationFormComponent,TicketDisplayComponent,ControlPanelComponent,LogDisplayComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ticketing-system';
  currentAvailableTickets=0;
  systemRunning=false;

  configureSystem(config:any){
    console.log("Configuration done:" + config)

    this.currentAvailableTickets=config.totalTickets;

  }
  handleStart(){
    console.log("System Started");
    this.systemRunning=true;
  }
  handleStop(){
    console.log("System Stoped");
    this.systemRunning=false;
  }

}
