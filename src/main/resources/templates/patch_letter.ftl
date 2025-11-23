<div style="font-family:Segoe UI, Roboto, Arial, sans-serif; font-size:13px; color:#222; line-height:1.4;">

    <h3 style="margin:0 0 12px 0; font-weight:600;">
        Patch Notification — Version ${ver}
    </h3>

    <p style="margin:0 0 10px 0;">
        A new application patch has been generated with the following distribution resources:
    </p>

    <div style="border-left:4px solid #4A90E2; padding:8px 12px; margin:12px 0; background:#f7f9fc;">
        <p style="margin:4px 0;">
            <strong>Patch package:</strong><br/>
            <a href="${patchUrl}" style="color:#0066cc; text-decoration:none;">${patchUrl}</a>
        </p>

        <p style="margin:4px 0;">
            <strong>SQL build script:</strong><br/>
            <a href="${sqlUrl}" style="color:#0066cc; text-decoration:none;">${sqlUrl}</a>
        </p>
    </div>

    <h4 style="margin:18px 0 8px 0; font-weight:600;">Included changes</h4>

    <ul style="padding-left:18px; margin:0;">
        <#list issues as issue>
        <li style="margin:4px 0;">
            <a href="https://jira.example.com/browse/${issue.key()}"
               style="color:#0052CC; text-decoration:none;">
                <strong>${issue.key()}</strong> — ${issue.summary()}
            </a>
        </li>
    </#list>
    </ul>

    <hr style="border:none; border-top:1px solid #ddd; margin:24px 0;">

    <div style="font-size:12px; color:#555;">
        <p style="margin:4px 0;">Regards,</p>
        <p style="margin:10px 0 0 0;">
            <a href="https://example.com"
               style="color:#4A90E2; text-decoration:none;">
                Company website
            </a>
        </p>
    </div>

</div>
