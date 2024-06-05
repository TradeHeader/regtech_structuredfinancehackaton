package cdm.regulation;

import cdm.regulation.New;
import cdm.regulation.Pric;
import cdm.regulation.Qty;
import cdm.regulation.Tx;
import cdm.regulation.Tx.TxBuilder;
import cdm.regulation.Tx.TxBuilderImpl;
import cdm.regulation.Tx.TxImpl;
import cdm.regulation.meta.TxMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="Tx", builder=Tx.TxBuilderImpl.class, version="${project.version}")
public interface Tx extends RosettaModelObject {

	TxMeta metaData = new TxMeta();

	/*********************** Getter Methods  ***********************/
	New getNewTx();
	String getTradDt();
	String getTradgCpcty();
	Qty getQty();
	Pric getPric();
	String getTradVn();
	String getCtryOfBrnch();

	/*********************** Build Methods  ***********************/
	Tx build();
	
	Tx.TxBuilder toBuilder();
	
	static Tx.TxBuilder builder() {
		return new Tx.TxBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Tx> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Tx> getType() {
		return Tx.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("newTx"), processor, New.class, getNewTx());
		processor.processBasic(path.newSubPath("tradDt"), String.class, getTradDt(), this);
		processor.processBasic(path.newSubPath("tradgCpcty"), String.class, getTradgCpcty(), this);
		processRosetta(path.newSubPath("qty"), processor, Qty.class, getQty());
		processRosetta(path.newSubPath("pric"), processor, Pric.class, getPric());
		processor.processBasic(path.newSubPath("tradVn"), String.class, getTradVn(), this);
		processor.processBasic(path.newSubPath("ctryOfBrnch"), String.class, getCtryOfBrnch(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface TxBuilder extends Tx, RosettaModelObjectBuilder {
		New.NewBuilder getOrCreateNewTx();
		New.NewBuilder getNewTx();
		Qty.QtyBuilder getOrCreateQty();
		Qty.QtyBuilder getQty();
		Pric.PricBuilder getOrCreatePric();
		Pric.PricBuilder getPric();
		Tx.TxBuilder setNewTx(New newTx);
		Tx.TxBuilder setTradDt(String tradDt);
		Tx.TxBuilder setTradgCpcty(String tradgCpcty);
		Tx.TxBuilder setQty(Qty qty);
		Tx.TxBuilder setPric(Pric pric);
		Tx.TxBuilder setTradVn(String tradVn);
		Tx.TxBuilder setCtryOfBrnch(String ctryOfBrnch);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("newTx"), processor, New.NewBuilder.class, getNewTx());
			processor.processBasic(path.newSubPath("tradDt"), String.class, getTradDt(), this);
			processor.processBasic(path.newSubPath("tradgCpcty"), String.class, getTradgCpcty(), this);
			processRosetta(path.newSubPath("qty"), processor, Qty.QtyBuilder.class, getQty());
			processRosetta(path.newSubPath("pric"), processor, Pric.PricBuilder.class, getPric());
			processor.processBasic(path.newSubPath("tradVn"), String.class, getTradVn(), this);
			processor.processBasic(path.newSubPath("ctryOfBrnch"), String.class, getCtryOfBrnch(), this);
		}
		

		Tx.TxBuilder prune();
	}

	/*********************** Immutable Implementation of Tx  ***********************/
	class TxImpl implements Tx {
		private final New newTx;
		private final String tradDt;
		private final String tradgCpcty;
		private final Qty qty;
		private final Pric pric;
		private final String tradVn;
		private final String ctryOfBrnch;
		
		protected TxImpl(Tx.TxBuilder builder) {
			this.newTx = ofNullable(builder.getNewTx()).map(f->f.build()).orElse(null);
			this.tradDt = builder.getTradDt();
			this.tradgCpcty = builder.getTradgCpcty();
			this.qty = ofNullable(builder.getQty()).map(f->f.build()).orElse(null);
			this.pric = ofNullable(builder.getPric()).map(f->f.build()).orElse(null);
			this.tradVn = builder.getTradVn();
			this.ctryOfBrnch = builder.getCtryOfBrnch();
		}
		
		@Override
		@RosettaAttribute("newTx")
		public New getNewTx() {
			return newTx;
		}
		
		@Override
		@RosettaAttribute("tradDt")
		public String getTradDt() {
			return tradDt;
		}
		
		@Override
		@RosettaAttribute("tradgCpcty")
		public String getTradgCpcty() {
			return tradgCpcty;
		}
		
		@Override
		@RosettaAttribute("qty")
		public Qty getQty() {
			return qty;
		}
		
		@Override
		@RosettaAttribute("pric")
		public Pric getPric() {
			return pric;
		}
		
		@Override
		@RosettaAttribute("tradVn")
		public String getTradVn() {
			return tradVn;
		}
		
		@Override
		@RosettaAttribute("ctryOfBrnch")
		public String getCtryOfBrnch() {
			return ctryOfBrnch;
		}
		
		@Override
		public Tx build() {
			return this;
		}
		
		@Override
		public Tx.TxBuilder toBuilder() {
			Tx.TxBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Tx.TxBuilder builder) {
			ofNullable(getNewTx()).ifPresent(builder::setNewTx);
			ofNullable(getTradDt()).ifPresent(builder::setTradDt);
			ofNullable(getTradgCpcty()).ifPresent(builder::setTradgCpcty);
			ofNullable(getQty()).ifPresent(builder::setQty);
			ofNullable(getPric()).ifPresent(builder::setPric);
			ofNullable(getTradVn()).ifPresent(builder::setTradVn);
			ofNullable(getCtryOfBrnch()).ifPresent(builder::setCtryOfBrnch);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Tx _that = getType().cast(o);
		
			if (!Objects.equals(newTx, _that.getNewTx())) return false;
			if (!Objects.equals(tradDt, _that.getTradDt())) return false;
			if (!Objects.equals(tradgCpcty, _that.getTradgCpcty())) return false;
			if (!Objects.equals(qty, _that.getQty())) return false;
			if (!Objects.equals(pric, _that.getPric())) return false;
			if (!Objects.equals(tradVn, _that.getTradVn())) return false;
			if (!Objects.equals(ctryOfBrnch, _that.getCtryOfBrnch())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (newTx != null ? newTx.hashCode() : 0);
			_result = 31 * _result + (tradDt != null ? tradDt.hashCode() : 0);
			_result = 31 * _result + (tradgCpcty != null ? tradgCpcty.hashCode() : 0);
			_result = 31 * _result + (qty != null ? qty.hashCode() : 0);
			_result = 31 * _result + (pric != null ? pric.hashCode() : 0);
			_result = 31 * _result + (tradVn != null ? tradVn.hashCode() : 0);
			_result = 31 * _result + (ctryOfBrnch != null ? ctryOfBrnch.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Tx {" +
				"newTx=" + this.newTx + ", " +
				"tradDt=" + this.tradDt + ", " +
				"tradgCpcty=" + this.tradgCpcty + ", " +
				"qty=" + this.qty + ", " +
				"pric=" + this.pric + ", " +
				"tradVn=" + this.tradVn + ", " +
				"ctryOfBrnch=" + this.ctryOfBrnch +
			'}';
		}
	}

	/*********************** Builder Implementation of Tx  ***********************/
	class TxBuilderImpl implements Tx.TxBuilder {
	
		protected New.NewBuilder newTx;
		protected String tradDt;
		protected String tradgCpcty;
		protected Qty.QtyBuilder qty;
		protected Pric.PricBuilder pric;
		protected String tradVn;
		protected String ctryOfBrnch;
	
		public TxBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("newTx")
		public New.NewBuilder getNewTx() {
			return newTx;
		}
		
		@Override
		public New.NewBuilder getOrCreateNewTx() {
			New.NewBuilder result;
			if (newTx!=null) {
				result = newTx;
			}
			else {
				result = newTx = New.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("tradDt")
		public String getTradDt() {
			return tradDt;
		}
		
		@Override
		@RosettaAttribute("tradgCpcty")
		public String getTradgCpcty() {
			return tradgCpcty;
		}
		
		@Override
		@RosettaAttribute("qty")
		public Qty.QtyBuilder getQty() {
			return qty;
		}
		
		@Override
		public Qty.QtyBuilder getOrCreateQty() {
			Qty.QtyBuilder result;
			if (qty!=null) {
				result = qty;
			}
			else {
				result = qty = Qty.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("pric")
		public Pric.PricBuilder getPric() {
			return pric;
		}
		
		@Override
		public Pric.PricBuilder getOrCreatePric() {
			Pric.PricBuilder result;
			if (pric!=null) {
				result = pric;
			}
			else {
				result = pric = Pric.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("tradVn")
		public String getTradVn() {
			return tradVn;
		}
		
		@Override
		@RosettaAttribute("ctryOfBrnch")
		public String getCtryOfBrnch() {
			return ctryOfBrnch;
		}
		
	
		@Override
		@RosettaAttribute("newTx")
		public Tx.TxBuilder setNewTx(New newTx) {
			this.newTx = newTx==null?null:newTx.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("tradDt")
		public Tx.TxBuilder setTradDt(String tradDt) {
			this.tradDt = tradDt==null?null:tradDt;
			return this;
		}
		@Override
		@RosettaAttribute("tradgCpcty")
		public Tx.TxBuilder setTradgCpcty(String tradgCpcty) {
			this.tradgCpcty = tradgCpcty==null?null:tradgCpcty;
			return this;
		}
		@Override
		@RosettaAttribute("qty")
		public Tx.TxBuilder setQty(Qty qty) {
			this.qty = qty==null?null:qty.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("pric")
		public Tx.TxBuilder setPric(Pric pric) {
			this.pric = pric==null?null:pric.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("tradVn")
		public Tx.TxBuilder setTradVn(String tradVn) {
			this.tradVn = tradVn==null?null:tradVn;
			return this;
		}
		@Override
		@RosettaAttribute("ctryOfBrnch")
		public Tx.TxBuilder setCtryOfBrnch(String ctryOfBrnch) {
			this.ctryOfBrnch = ctryOfBrnch==null?null:ctryOfBrnch;
			return this;
		}
		
		@Override
		public Tx build() {
			return new Tx.TxImpl(this);
		}
		
		@Override
		public Tx.TxBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Tx.TxBuilder prune() {
			if (newTx!=null && !newTx.prune().hasData()) newTx = null;
			if (qty!=null && !qty.prune().hasData()) qty = null;
			if (pric!=null && !pric.prune().hasData()) pric = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getNewTx()!=null && getNewTx().hasData()) return true;
			if (getTradDt()!=null) return true;
			if (getTradgCpcty()!=null) return true;
			if (getQty()!=null && getQty().hasData()) return true;
			if (getPric()!=null && getPric().hasData()) return true;
			if (getTradVn()!=null) return true;
			if (getCtryOfBrnch()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Tx.TxBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Tx.TxBuilder o = (Tx.TxBuilder) other;
			
			merger.mergeRosetta(getNewTx(), o.getNewTx(), this::setNewTx);
			merger.mergeRosetta(getQty(), o.getQty(), this::setQty);
			merger.mergeRosetta(getPric(), o.getPric(), this::setPric);
			
			merger.mergeBasic(getTradDt(), o.getTradDt(), this::setTradDt);
			merger.mergeBasic(getTradgCpcty(), o.getTradgCpcty(), this::setTradgCpcty);
			merger.mergeBasic(getTradVn(), o.getTradVn(), this::setTradVn);
			merger.mergeBasic(getCtryOfBrnch(), o.getCtryOfBrnch(), this::setCtryOfBrnch);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Tx _that = getType().cast(o);
		
			if (!Objects.equals(newTx, _that.getNewTx())) return false;
			if (!Objects.equals(tradDt, _that.getTradDt())) return false;
			if (!Objects.equals(tradgCpcty, _that.getTradgCpcty())) return false;
			if (!Objects.equals(qty, _that.getQty())) return false;
			if (!Objects.equals(pric, _that.getPric())) return false;
			if (!Objects.equals(tradVn, _that.getTradVn())) return false;
			if (!Objects.equals(ctryOfBrnch, _that.getCtryOfBrnch())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (newTx != null ? newTx.hashCode() : 0);
			_result = 31 * _result + (tradDt != null ? tradDt.hashCode() : 0);
			_result = 31 * _result + (tradgCpcty != null ? tradgCpcty.hashCode() : 0);
			_result = 31 * _result + (qty != null ? qty.hashCode() : 0);
			_result = 31 * _result + (pric != null ? pric.hashCode() : 0);
			_result = 31 * _result + (tradVn != null ? tradVn.hashCode() : 0);
			_result = 31 * _result + (ctryOfBrnch != null ? ctryOfBrnch.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TxBuilder {" +
				"newTx=" + this.newTx + ", " +
				"tradDt=" + this.tradDt + ", " +
				"tradgCpcty=" + this.tradgCpcty + ", " +
				"qty=" + this.qty + ", " +
				"pric=" + this.pric + ", " +
				"tradVn=" + this.tradVn + ", " +
				"ctryOfBrnch=" + this.ctryOfBrnch +
			'}';
		}
	}
}
