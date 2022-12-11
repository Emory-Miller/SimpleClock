//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame {

    Calendar calendar;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;

    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;
    JButton hrFormat;
    JButton GMTSwitch;
    String time;
    String day;
    String date;
    boolean hrToggle = true;
    boolean GMTToggle = true;

    SimpleClock() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Digital Clock");
        this.setLayout(new FlowLayout());
        this.setSize(400, 250);
        this.setResizable(false);

        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setOpaque(true);
        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Ink Free", Font.BOLD, 34));

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Ink Free", Font.BOLD, 30));

        hrFormat = new JButton("Time Format");
        hrFormat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hrToggle) {
                    timeFormat = new SimpleDateFormat("kk:mm:ss");
                    hrToggle = false;
                } else {
                    timeFormat = new SimpleDateFormat("hh:mm:ss a");
                    hrToggle = true;
                }
            }
        });


        GMTSwitch = new JButton("GMT/EST");
        GMTSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GMTToggle) {
                    timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    GMTToggle = false;
                } else {
                    timeFormat.setTimeZone(TimeZone.getTimeZone("EST"));
                    GMTToggle = true;
                }
            }
        });


        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);
        this.add(hrFormat);
        this.add(GMTSwitch);
        this.setVisible(true);

//            setTimer();
        rumSimpleClock();
    }

//        public void setTimer() {
//            while (true) {
//                time = timeFormat.format(Calendar.getInstance().getTime());
//                timeLabel.setText(time);
//
//                day = dayFormat.format(Calendar.getInstance().getTime());
//                dayLabel.setText(day);
//
//                date = dateFormat.format(Calendar.getInstance().getTime());
//                dateLabel.setText(date);
//
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                    e.getStackTrace();
//                }
//            }
//        }

    public void rumSimpleClock() {
        time = timeFormat.format(Calendar.getInstance().getTime());
        timeLabel.setText(time);

        day = dayFormat.format(Calendar.getInstance().getTime());
        dayLabel.setText(day);

        date = dateFormat.format(Calendar.getInstance().getTime());
        dateLabel.setText(date);
        Thread thread = new Thread(this::rumSimpleClock);
        thread.start();
    }

    public static void main(String[] args) {
        new SimpleClock();
    }
}
