   M S B A M L   `   `   `  ����E  AMqttWpfApp, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullG(clr-namespace:MqttMoniteringWpfApp.ViewsMqttMoniteringWpfApp.Views  5      ,    &MqttMoniteringWpfApp.Views.HistoryView   5      R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5      R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      R NOxyPlot.Wpf, Version=2.0.0.0, Culture=neutral, PublicKeyToken=75e952ba404cdbb0 oxyhttp://oxyplot.org/wpf  5      :d2http://schemas.microsoft.com/expression/blend/2008  5      4local(clr-namespace:MqttMoniteringWpfApp.Views  5      .��5
      �   � RowDefinitions  5   
   �� 5      $��50 �6   �� 5      $��* �6   .+�5   
   �� $��IoT History Datas��6      MahApps.Styles.Label#���   6,   $��25(�5      $��Black"�6   � 5   
   $��1��6     5      �� 5      $��40 �6    �� 5      $��* �6     � ColumnDefinitions 5      �� 5      $��* �6#   �� 5      $	��100 �6#   .+�5      ��  �� Orientation$ 
Horizontal=�6   .+�5      �� $��Division��6   $��Center=�6+   �� 5      -   5         ��Name$ 	Divisions��5      $	��150��6.    �� DisplayMemberPath$ DivisionVal��6:     MahApps.Styles.ComboBox#���  5       $��Center=�5!       �� SelectedItem 5      �� *SelectedDivision+5!      �� 5"      $��  StartDate��6   $��Center=�6.   (  "System.Windows.Controls.DatePicker  5#      -   5          Name$ 	StartDate��5#      $	��100��60   $��Center=�6<     MahApps.Styles.DatePicker#���  5$      �� 5%      $��	  EndDate��6   $��Center=�6,     5&      -   5        $ EndDate��5&      $	��100��6.   $��Center=�6:   #���  5'      �� 5(      $��  Total Count��6   $��Center=�60   �� 5)      -   5         ��Name$ 
TotalCount��5)      $��  Total Count��6,   $��Center=�6D   5*      �� 5,      -   5         ��Name$ Search��5,      $��1��6%   $��SEARCH��65    ( $MahApps.Styles.Button.Dialogs.Accent#���  5-      � 5/      $��1��6   $��0��6    $��2��60   $��10q�6D   .+�50        OxyPlot.Wpf.Plot  
�������	   Series	 51        OxyPlot.Wpf.LineSeries  52        OxyPlot.Wpf.Series
   Color$
 DarkRed��6)      
MarkerType$
 None=�69      Title$ Temperature��6K   ��53   )   �� *
TempValues+  54      $
 
Aquamarine��6)   $
 None=�6<   $ Humidity��6N   ��55   )   �� *HumidValues+  56      $
 
BlueViolet��6)   $
 None=�6<   $ Pressure��6N   ��57   )   �� *PressValues+59      5:      5;      5<      5=      