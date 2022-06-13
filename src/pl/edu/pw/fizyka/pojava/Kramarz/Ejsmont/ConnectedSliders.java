package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;
//author Natalia Kramarz
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class SliderGroup {
	
    private final Map<JSlider, Integer> values;
    private final LinkedList<JSlider> candidates;
    private final ChangeListener changeListener;
    private boolean updating = false;
    
    SliderGroup() {
        this.values = new HashMap<JSlider, Integer>();
        this.candidates = new LinkedList<JSlider>();
        
        changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                updateSliders(source);
            }
        };//end ChangeListener
    }//endSliderGroup

    private void updateSliders(JSlider source) {
        if (updating) {
            return;
        }
        updating = true;
        int delta = source.getValue() - values.get(source);
        if (delta > 0) {
            distributeRemove(delta, source);
        }
        
        else {
            distributeAdd(delta, source);
        }
        
        for (JSlider slider : candidates) {
            values.put(slider, slider.getValue());
        }

        updating = false;
    }//end updateSLiders
    
    private void distributeRemove(int delta, JSlider source) {
        int counter = 0;
        int remaining = delta;
        while (remaining > 0) {
            JSlider slider = candidates.removeFirst();
            counter++;

            if (slider == source) {
                candidates.addLast(slider);
            }
            else {
                if (slider.getValue() > 0 && slider.isEnabled()) {
                    slider.setValue(slider.getValue()-1);
                    remaining--;
                    counter = 0;
                }
                candidates.addLast(slider);
                if (remaining == 0) {
                    break;
                }
            }
            if (counter > candidates.size()) {
                String message = "Can not distribute "+delta+" among "+candidates;
                throw new IllegalArgumentException(message);
            }
        }
    }//end distributeSSliders

    private void distributeAdd(int delta, JSlider source) {
        int counter = 0;
        int remaining = -delta;
        while (remaining > 0) {
            JSlider slider = candidates.removeLast();
            counter++;
            if (slider == source) {
                candidates.addFirst(slider);
            }
            else {
                if (slider.getValue() < slider.getMaximum( )&& slider.isEnabled()) {
                    slider.setValue(slider.getValue()+1);
                    remaining--;
                    counter = 0;
                }
                candidates.addFirst(slider);
                if (remaining == 0) {
                    break;
                }
            }
            
            if (counter > candidates.size()) {
                String message = "Can not distribute "+delta+" among "+candidates;
                throw new IllegalArgumentException(message);
            }
        }
    }//end distributeAdd
    
    public void add(JSlider slider) {
        candidates.add(slider);
        values.put(slider, slider.getValue());
        slider.addChangeListener(changeListener);
    }//end add
    
    void remove(JSlider slider) {
        candidates.remove(slider);
        values.remove(slider);
        slider.removeChangeListener(changeListener);
    }//end remove
}//end sliderGroup class