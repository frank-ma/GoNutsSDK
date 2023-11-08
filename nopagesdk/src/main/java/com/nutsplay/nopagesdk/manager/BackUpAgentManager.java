package com.nutsplay.nopagesdk.manager;

import android.app.backup.BackupAgent;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.os.ParcelFileDescriptor;

import com.nutspower.commonlibrary.utils.LogUtils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by frankma on 2023/11/4 11:07 AM
 * Email: frankma9103@gmail.com
 * Desc:  Google数据备份管理器，将数据备份到Google云端硬盘
 *
 */
public class BackUpAgentManager extends BackupAgent {

    public static BackUpAgentManager INSTANCE;
    private static String TAG = "BackUpAgentManager";
    private static String UserKey = "UserKey";
    public static BackUpAgentManager getInstance() {
        if (INSTANCE == null) {
            synchronized (BackUpAgentManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BackUpAgentManager();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 数据备份
     * @param oldState An open, read-only ParcelFileDescriptor pointing to the
     *            last backup state provided by the application. May be
     *            <code>null</code>, in which case no prior state is being
     *            provided and the application should perform a full backup.
     * @param data A structured wrapper around an open, read/write
     *            file descriptor pointing to the backup data destination.
     *            Typically the application will use backup helper classes to
     *            write to this file.
     * @param newState An open, read/write ParcelFileDescriptor pointing to an
     *            empty file. The application should record the final backup
     *            state here after writing the requested data to the <code>data</code>
     *            output stream.
     * @throws IOException
     */
    @Override
    public void onBackup(ParcelFileDescriptor oldState, BackupDataOutput data, ParcelFileDescriptor newState) throws IOException {
        LogUtils.e(TAG,"onBackup");
    }

    /**
     * 数据恢复
     * @param data A structured wrapper around an open, read-only
     *            file descriptor pointing to a full snapshot of the
     *            application's data.  The application should consume every
     *            entity represented in this data stream.
     * @param appVersionCode The value of the <a
     * href="{@docRoot}guide/topics/manifest/manifest-element.html#vcode">{@code
     *            android:versionCode}</a> manifest attribute,
     *            from the application that backed up this particular data set. This
     *            makes it possible for an application's agent to distinguish among any
     *            possible older data versions when asked to perform the restore
     *            operation.
     * @param newState An open, read/write ParcelFileDescriptor pointing to an
     *            empty file. The application should record the final backup
     *            state here after restoring its data from the <code>data</code> stream.
     *            When a full-backup dataset is being restored, this will be <code>null</code>.
     * @throws IOException
     *
     * 只有备份管理器可以调用 onRestore()，当系统安装您的应用并找到现有备份数据时，该调用会自动发生。
     */
    @Override
    public void onRestore(BackupDataInput data, int appVersionCode, ParcelFileDescriptor newState) throws IOException {
        LogUtils.e(TAG,"onRestore");

        while (data.readNextHeader()) {
            String key = data.getKey();
            int dataSize = data.getDataSize();

            // If the key is ours (for saving top score). Note this key was used when
            // we wrote the backup entity header
            if (UserKey.equals(key)) {
                // Create an input stream for the BackupDataInput
                byte[] dataBuf = new byte[dataSize];
                data.readEntityData(dataBuf, 0, dataSize);
                ByteArrayInputStream baStream = new ByteArrayInputStream(dataBuf);
                DataInputStream in = new DataInputStream(baStream);

                // Read the player name and score from the backup data
//                playerName = in.readUTF();
//                playerScore = in.readInt();

                // Record the score on the device (to a file or something)
//                recordScore(playerName, playerScore);
            } else {
                data.skipEntityData();
            }
        }

        // Finally, write to the state blob (newState) that describes the restored data
        FileOutputStream outstream = new FileOutputStream(newState.getFileDescriptor());
        DataOutputStream out = new DataOutputStream(outstream);
//        out.writeUTF(playerName);
//        out.writeInt(mPlayerScore);
    }
}
