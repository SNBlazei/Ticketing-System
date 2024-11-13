import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-ticket-display',
  standalone: true,
  imports: [],
  templateUrl: './ticket-display.component.html',
  styleUrls: ['./ticket-display.component.css']
})
export class TicketDisplayComponent {

  @Input() availableTickets:number=0

}
