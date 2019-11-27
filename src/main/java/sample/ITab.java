package sample;

import sample.interfaces.AutowireInterface;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/28 15:26
 */
public interface ITab extends AutowireInterface {
    void onSelect();

    void onAppClose();
}
