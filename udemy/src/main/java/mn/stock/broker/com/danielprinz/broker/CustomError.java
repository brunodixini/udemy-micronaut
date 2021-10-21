package mn.stock.broker.com.danielprinz.broker;

public class CustomError {

    private int status;
    private String error;
    private String message;
    private String path;

    public CustomError(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;

        this.path = path;
    }

    public CustomError() {
    }

    public static CustomErrorBuilder builder() {
        return new CustomErrorBuilder();
    }

    public int getStatus() {
        return this.status;
    }

    public String getError() {
        return this.error;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPath() {
        return this.path;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CustomError)) return false;
        final CustomError other = (CustomError) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getStatus() != other.getStatus()) return false;
        final Object this$error = this.getError();
        final Object other$error = other.getError();
        if (this$error == null ? other$error != null : !this$error.equals(other$error)) return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        final Object this$path = this.getPath();
        final Object other$path = other.getPath();
        if (this$path == null ? other$path != null : !this$path.equals(other$path)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CustomError;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getStatus();
        final Object $error = this.getError();
        result = result * PRIME + ($error == null ? 43 : $error.hashCode());
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        final Object $path = this.getPath();
        result = result * PRIME + ($path == null ? 43 : $path.hashCode());
        return result;
    }

    public String toString() {
        return "CustomError(status=" + this.getStatus() + ", error=" + this.getError() + ", message=" + this.getMessage() + ", path=" + this.getPath() + ")";
    }

    public static class CustomErrorBuilder {
        private int status;
        private String error;
        private String message;
        private String path;

        CustomErrorBuilder() {
        }

        public CustomErrorBuilder status(int status) {
            this.status = status;
            return this;
        }

        public CustomErrorBuilder error(String error) {
            this.error = error;
            return this;
        }

        public CustomErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        public CustomErrorBuilder path(String path) {
            this.path = path;
            return this;
        }

        public CustomError build() {
            return new CustomError(status, error, message, path);
        }

        public String toString() {
            return "CustomError.CustomErrorBuilder(status=" + this.status + ", error=" + this.error + ", message=" + this.message + ", path=" + this.path + ")";
        }
    }
}
