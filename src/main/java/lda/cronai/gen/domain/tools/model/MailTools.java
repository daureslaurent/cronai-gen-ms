package lda.cronai.gen.domain.tools.model;

import org.springframework.stereotype.Component;

@Component
public class MailTools extends ToolsAbstract {

    private static final String ID = "MAIL_TOOL";
    private static final String NAME = "MAIL_TOOL";
    private static final String DESCRIPTION = "MAIL_TOOL";

    public MailTools() {
        super(ID, NAME, DESCRIPTION);
    }
}
