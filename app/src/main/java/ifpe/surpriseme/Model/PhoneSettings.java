package ifpe.surpriseme.Model;

public class PhoneSettings {

    private boolean saveOnDevice;
    private String timeFrequency; //hour, minute, second
    private String valueFrequency; //1,2,3,4

    public PhoneSettings(String valueFrequency, String timeFrequency, boolean saveOnDevice){

        this.valueFrequency = valueFrequency;
        this.saveOnDevice = saveOnDevice;
        this.timeFrequency = timeFrequency;
    }

    public String getTimeFrequency() {
        return timeFrequency;
    }

    public void setTimeFrequency(String timeFrequency) {
        this.timeFrequency = timeFrequency;
    }

    public String getValueFrequency() {
        return valueFrequency;
    }

    public void setValueFrequency(String valueFrequency) {
        this.valueFrequency = valueFrequency;
    }

    public boolean isSaveOnDevice() {
        return saveOnDevice;
    }

    public void setSaveOnDevice(boolean saveOnDevice) {
        this.saveOnDevice = saveOnDevice;
    }


}
