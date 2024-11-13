import { Component,Output,EventEmitter } from '@angular/core';

@Component({
  selector: 'app-control-panel',
  standalone: true,
  imports: [],
  templateUrl: './control-panel.component.html',
  styleUrls: ['./control-panel.component.css']
})
export class ControlPanelComponent {
  @Output() onStart=new EventEmitter<void>();
  @Output() onStop=new EventEmitter<void>();


  startSystem(){
    this.onStart.emit();
  }
  stopSystem(){
    this.onStop.emit();
  }


}
