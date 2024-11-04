import { config } from './app.config.server';
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfigurationFormComponent } from './configuration-form/configuration-form.component';
import { TicketDisplayComponent } from './ticket-display/ticket-display.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule,ConfigurationFormComponent,TicketDisplayComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ticketing-system';
  currentAvailableTickets=0;

  configureSystem(config:any){
    console.log("Configuration done:" + config)

    this.currentAvailableTickets=config.totalTickets;

  }
}
